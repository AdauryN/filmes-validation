import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterOutlet, RouterLink } from '@angular/router';
import { DemoNgZorroAntdModule } from '../../DemoNgZorroAntdModule';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    FormsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule,
    RouterOutlet,
    RouterLink
  ],
  exports: [
    CommonModule,
    FormsModule,
    DemoNgZorroAntdModule,
    ReactiveFormsModule,
    RouterOutlet,
    RouterLink
  ]
})
export class SharedModule { }
