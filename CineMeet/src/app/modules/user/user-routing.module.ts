import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FriendsComponent } from './components/friends/friends.component';
import { CreateEventComponent } from './components/create-event/create-event.component';
import { ViewEventsComponent } from './components/view-events/view-events.component';
import { ViewEventComponent } from './components/view-event/view-event.component';

const routes: Routes = [
  {path: "dashboard", component:DashboardComponent},
  {path: "friends", component:FriendsComponent},
  {path: "create-event", component:CreateEventComponent},
  {path: "view-events", component:ViewEventsComponent},
  {path: "view-event/:id", component: ViewEventComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
