import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { ActivatedRoute, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../../../services/auth.service';
import { EventService } from '../../services/event.service';
import { EventRequestService } from '../../services/event-request.service';
import { UserStorageService } from '../../../../services/user-storage.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReviewService } from '../../services/review.service';

@Component({
  selector: 'app-view-event',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './view-event.component.html',
  styleUrl: './view-event.component.scss'
})
export class ViewEventComponent {

  event: any;
  myRequest: any;
  id: any;
  loggedInUser = UserStorageService.getUserId();

  reviewForm:FormGroup;

  constructor(private eventRequestService: EventRequestService,
    private eventService: EventService,
    private notification: NzNotificationService,
    private reviewService: ReviewService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.id = +params['id']; // Convert the ID parameter from string to number

      this.reviewForm = this.fb.group({
        rating: [null, Validators.required],
        review: [null, Validators.required]
        
      });

      this.getEvent();
      this.getApprovedRequests();
      this.checkMyStatus();
    });
  }

  getEvent() {
    this.eventService.getEvent(this.id).subscribe(res => {
      this.event = res;
      this.getPendingOrCanceledRequests();
      this.getUserReview();
    }, error => {
      this.notification
        .error(
          'ERROR',
          `${error.error}`,
          { nzDuration: 5000 }
        );
    })
  }

  checkMyStatus() {
    this.eventRequestService.checkMyStatus(this.id).subscribe(res => {
      this.myRequest = res;
    })
  }

  ///////


  isVisible = false;
  inputValue?: string;

  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    console.log('Button ok clicked!');

    const data: any = {
      eventId: this.id,
      userId: UserStorageService.getUserId()
    }
    if (this.inputValue != null && this.inputValue != '') {
      data.whatBringing = this.inputValue;
    }
    this.eventRequestService.createEventRequest(data).subscribe(res => {
      // Show success notification on successful showroom creation
      this.notification
        .success(
          'SUCCESS',
          `Event Request Created Successfully.`,
          { nzDuration: 5000 } // Set notification duration
        );
      this.isVisible = false;
      this.inputValue = null;
      this.checkMyStatus();
      this.getPendingOrCanceledRequests();
    }, error => {
      // Show error notification on failure
      this.notification
        .error(
          'ERROR',
          `${error.error}`, // Display error message
          { nzDuration: 5000 } // Set notification duration
        );
    })
  }

  handleCancel(): void {
    console.log('Button cancel clicked!');
    this.isVisible = false;
  }

  ///////////

  pendingOrCanceledRequests: any;
  approvedRequests: any;

  getPendingOrCanceledRequests() {
    console.log(this.event.userId == this.loggedInUser)
    if (this.event.userId == this.loggedInUser) {
      this.eventRequestService.getPendingOrCanceledRequests(this.id).subscribe(res => {
        this.pendingOrCanceledRequests = res;
      }, error => {
        this.notification
          .error(
            'ERROR',
            `${error.error}`, // Display error message
            { nzDuration: 5000 } // Set notification duration
          );
      })
    }
  }

  getApprovedRequests() {
    this.eventRequestService.getApprovedRequests(this.id).subscribe(res => {
      this.approvedRequests = res;
    }, error => {
      this.notification
        .error(
          'ERROR',
          `${error.error}`, // Display error message
          { nzDuration: 5000 } // Set notification duration
        );
    })
  }


  updateRequestStatus(id: any, status: any) {
    if (this.event.userId == this.loggedInUser) {
      this.eventRequestService.updateRequestStatus(id, status).subscribe(res => {
        // Show success notification on successful showroom creation
        this.notification
          .success(
            'SUCCESS',
            `Request Status Updated Successfully.`,
            { nzDuration: 5000 } // Set notification duration
          );
        this.getApprovedRequests();
        this.getPendingOrCanceledRequests();
      }, error => {
        // Show error notification on failure
        this.notification
          .error(
            'ERROR',
            `${error.error}`, // Display error message
            { nzDuration: 5000 } // Set notification duration
          );
      })
    }
  }

  get isEventPast(): boolean {
    const currentTime = new Date();
    const eventDate = new Date(this.event.date); 
    console.log(currentTime, this.event.date, currentTime > this.event.date)
    return currentTime > eventDate;  
  }

  ///////////////



  isVisibleReview = false;

  showModalReview(): void {
    this.isVisibleReview = true;
  }

  handleOkReview(): void {
    console.log('Button ok clicked!');

    const data: any = {
      eventId: this.id,
      reviewerId: UserStorageService.getUserId(),
      rating: this.reviewForm.get("rating").value,
        review: this.reviewForm.get("review").value,
    }
   
    this.reviewService.giveReview(data).subscribe(res => {
      // Show success notification on successful showroom creation
      this.notification
        .success(
          'SUCCESS',
          `Review Created Successfully.`,
          { nzDuration: 5000 } // Set notification duration
        );
      this.isVisibleReview = false;
      this.reviewForm.reset();
      this.getUserReview();
    }, error => {
      // Show error notification on failure
      this.notification
        .error(
          'ERROR',
          `${error.error}`, // Display error message
          { nzDuration: 5000 } // Set notification duration
        );
    })
  }

  handleCancelReview(): void {
    console.log('Button cancel clicked!');
    this.isVisibleReview = false;
  }

  readonly tooltips = ['Terrible', 'Bad', 'Normal', 'Good', 'Wonderful'];
  reviews:any;
  getUserReview(){
    this.reviewService.getAllReviewsOfUser(this.event.userId).subscribe(res=>{
      this.reviews = res;
    })
  }
}
