import { Routes } from '@angular/router';
import { LoginComponent } from './modules/auth/login/login.component';
import { SignupComponent } from './modules/auth/signup/signup.component';

export const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'register', component: SignupComponent},
    {
        path: 'user',
        loadChildren: () => import('./modules/user/user.module').then(m => m.UserModule)
      },
      { path: '**', redirectTo: '', pathMatch: 'full' }
];
