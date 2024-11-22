import { Component } from '@angular/core';
import { EventService } from '../../services/event.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStorageService } from '../../../../services/user-storage.service';
import { SharedModule } from '../../../shared/shared.module';

@Component({
  selector: 'app-create-event',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './create-event.component.html',
  styleUrl: './create-event.component.scss'
})
export class CreateEventComponent {
  constructor(private fb: FormBuilder, // Inject FormBuilder for building reactive forms
    private eventService: EventService, // Inject user car service for API interactions
    private notification: NzNotificationService, // Inject notification service for displaying messages
    private router: Router, // Inject router for navigation
    private activatedRoute: ActivatedRoute // Inject activated route for accessing route parameters
  ) { }

  eventForm: FormGroup; // Declare a FormGroup for the car showroom form

  ngOnInit(): void {
      // Initialize the car showroom form with validators
      this.eventForm = this.fb.group({
        title: [
          '', // Initial value for showroom name
          [Validators.required] // Required and max length validation
        ],
        description: [
          '', // Initial value for commercial registration number
          [Validators.required] // Required and pattern validation for 10-digit numbers
        ],
        movieName: [
          '', // Initial value for manager name
          [Validators.required] // Required and max length validation
        ],
        movieImgUrl: [
          '', // Initial value for contact number
          [Validators.required] // Required and pattern validation for 1 to 15-digit numbers
        ],
        date: [
          '', // Initial value for address
          [Validators.required] // Required and max length validation
        ]
      });
  }

  onSubmit() {
    console.log(this.eventForm.valid) // Log the validity of the form
    if (this.eventForm.valid) { // Check if the form is valid
      const data = this.eventForm.value; // Get the form data
      data.userId = UserStorageService.getUserId(); // Add user ID to the data

      // Post the showroom data to the server
      this.eventService.postEvent(data).subscribe(res => {
        // Show success notification on successful showroom creation
        this.notification
          .success(
            'SUCCESS',
            `Event Created Successfully.`,
            { nzDuration: 5000 } // Set notification duration
          );
        this.router.navigateByUrl("/user/view-events") // Navigate to the view showrooms page
      }, error => {
        // Show error notification on failure
        this.notification
          .error(
            'ERROR',
            `${error.error}`, // Display error message
            { nzDuration: 5000 } // Set notification duration
          );
      })
    } else {
      // If form is invalid, mark all controls as dirty to show validation messages
      for (const i in this.eventForm.controls) {
        this.eventForm.controls[i].markAsDirty(); // Mark the control as dirty
        this.eventForm.controls[i].updateValueAndValidity(); // Update the validity state
      }
    }
  }

}

