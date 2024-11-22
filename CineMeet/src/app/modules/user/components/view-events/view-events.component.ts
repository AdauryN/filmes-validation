import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { EventService } from '../../services/event.service';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../../../services/auth.service';

@Component({
  selector: 'app-view-events',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './view-events.component.html',
  styleUrl: './view-events.component.scss'
})
export class ViewEventsComponent {

  events:any;

  constructor(private authService: AuthService,
    private eventService: EventService,
    private notification: NzNotificationService,
    private router: Router
  ){}

  ngOnInit(): void {
    // Lifecycle hook that runs after component initialization
    this.getFutureFriendsEvents();
  }

  radioValue = 'friends';
  
  getFutureEvents(){
    this.eventService.getFutureEvents().subscribe(res=>{
      this.events = res;
    })
  }

  getFutureFriendsEvents(){
    this.eventService.getFutureFriendsEvents().subscribe(res=>{
      this.events = res;
    })
  }

  typeChanged(){
    console.log("called",  this.radioValue);
    if(this.radioValue == 'all'){
      this.getFutureEvents();
    }else{
      this.getFutureFriendsEvents();
    }
  }

}
