import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FriendService } from '../../services/friend.service';
import { UserStorageService } from '../../../../services/user-storage.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-friends',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './friends.component.html',
  styleUrl: './friends.component.scss'
})
export class FriendsComponent {
  userId = UserStorageService.getUserId();
  friendRequests: any = [];
  friends: any = [];
  message: string = '';

  constructor(private friendService: FriendService,
    private notification: NzNotificationService,
  ) {}

  ngOnInit(): void {
    this.loadFriendRequests();
    this.loadFriends();
  }

  // sendFriendRequest(friendId: any): void {
  //   this.friendService.sendFriendRequest(this.userId, friendId).subscribe(
  //     response => {
  //       this.message = response;
  //       this.loadFriendRequests();
  //     },
  //     error => {
  //       this.message = 'Error sending friend request: ' + error.error;
  //     }
  //   );
  // }

  loadFriendRequests(): void {
    this.friendService.getFriendRequests(UserStorageService.getUserId()).subscribe(
      requests => (this.friendRequests = requests),
      error => console.error('Error loading friend requests', error)
    );
  }

  loadFriends(): void {
    this.friendService.getFriends(UserStorageService.getUserId()).subscribe(
      friends => (this.friends = friends),
      error => console.error('Error loading friends', error)
    );
  }

  respondToFriendRequest(requestId: number, status: 'ACCEPTED' | 'REJECTED'): void {
    this.friendService.respondToFriendRequest(requestId, status).subscribe(
      response => {
        this.notification // Show success notification
            .success(
              'SUCCESS',
              `Friend Request Updated Successfully.`,
              { nzDuration: 5000 } // Set notification duration
            );
        this.loadFriendRequests();
        this.loadFriends();
      },
      error => {
        this.notification // Show success notification
        .error(
          'ERROR',
          `${error.error}`,
          { nzDuration: 5000 } // Set notification duration
        );
      }
    );
  }
}