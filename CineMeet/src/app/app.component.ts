import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { SharedModule } from './modules/shared/shared.module';
import { UserStorageService } from './services/user-storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, SharedModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'CineMeet';

  // Boolean flags to check if the user or admin is logged in
  isUserLoggedIn: boolean = UserStorageService.isUserLoggedIn();
  loggedInUserName: string = UserStorageService.getUserName();

  // Constructor to inject the Router service
  constructor(private router: Router) { }

  // Lifecycle hook that is called after the component has been initialized
  ngOnInit(): void {
    // Subscribe to router events to update login status on navigation
    this.router.events.subscribe(event => {
      // Check if the navigation event has ended
      if (event.constructor.name === "NavigationEnd") {
        // Update login status for user and admin
        this.isUserLoggedIn = UserStorageService.isUserLoggedIn();
        this.loggedInUserName = UserStorageService.getUserName();
      }
    });
  }

  // Method to log out the user
  logout() {
    UserStorageService.signOut();  // Call the signOut method from UserStorageService
    this.router.navigateByUrl('/');  // Navigate back to the home page after logout
  }
}
