import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [SharedModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  registerForm!: FormGroup;

  constructor(private fb: FormBuilder,
            private authService: AuthService,
            private message: NzMessageService,
            private router: Router){}

  ngOnInit(){
    this.registerForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, Validators.required],
      name: [null, Validators.required]
    })
  }

  submitForm(){
    this.authService.register(this.registerForm.value).subscribe(res=>{
      if(res.id !=null){
        this.message.success("Signup successful",  { nzDuration: 5000 });
        this.router.navigateByUrl("/");
      } else{
        this.message.error(`${res.message}`, { nzDuration: 5000 })
      }
    }, error=>{
      this.message.error(`${error.error}`, { nzDuration: 5000 })
    })
  }

}

