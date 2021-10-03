import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import { ProfileFormComponent } from './profile-form/profile-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ProfileFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
