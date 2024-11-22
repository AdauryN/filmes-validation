import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { AuthService } from '../../../../services/auth.service';
import { FriendService } from '../../services/friend.service';
import { UserStorageService } from '../../../../services/user-storage.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  users:any;

  constructor(private authService: AuthService,
    private friendService: FriendService,
    private notification: NzNotificationService,
    private router: Router
  ){}

  ngOnInit(): void {
    // Lifecycle hook that runs after component initialization
    this.getAllUser();
  }
  getAllUser(){
    this.authService.getAllUsers().subscribe(res=>{
      this.users = res;
    }, error=>{

    })
  }

  sendFriendRequest(friendId: any): void {
    this.friendService.sendFriendRequest(UserStorageService.getUserId(), friendId).subscribe(
      response => {
        this.notification // Show success notification
            .success(
              'SUCCESS',
              `Friend Request Sent Successfully.`,
              { nzDuration: 5000 } // Set notification duration
            );
        this.router.navigateByUrl("/user/friends");
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

  readonly tooltips = ['Terrible', 'Bad', 'Normal', 'Good', 'Wonderful'];


}
