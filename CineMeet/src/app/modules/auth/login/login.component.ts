import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AuthService } from '../../../services/auth.service';
import { UserStorageService } from '../../../services/user-storage.service';
import { SharedModule } from '../../shared/shared.module';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm!: FormGroup;
  isSpinning = false;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private message: NzMessageService,
    private router: Router,) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
    });
  }

  submitForm(): void {
    this.isSpinning = true;
    this.authService.login(this.loginForm.value).subscribe(
      (res) => {
        console.log(res);
        if (res.id != null) {
          const user = {
            id: res.id,
            name: res.name,
            role: "USER"
          }
          UserStorageService.saveUser(user);
          this.isSpinning = false;
         if (UserStorageService.isUserLoggedIn()) {
            this.router.navigateByUrl('/user/dashboard');
          }
        } else {
          this.isSpinning = false;
          this.message
            .error(
              `Informações erradas`,
              { nzDuration: 5000 }
            )
        }
      }, error=>{
        this.isSpinning = false;
          this.message
            .error(
              `${error.error}`,
              { nzDuration: 5000 }
            )
      })
  }

}