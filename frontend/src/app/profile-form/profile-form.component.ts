import {Component, OnInit} from '@angular/core';
import {Industry} from "../model/industry";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {FormBuilder, Validators} from "@angular/forms";
import {Profile} from "../model/profile";

@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.css']
})
export class ProfileFormComponent implements OnInit {

  industries: Industry[] = [];
  extractedIndustries: { uuid: number, name: string }[] = [];
  currentProfileUuid: number | undefined;

  apiURL = 'http://localhost:8080/test-task';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  profileForm = this.fb.group({
    name: ['', Validators.required],
    industry: ['', Validators.required],
    agreement: ['', Validators.requiredTrue]
  });

  constructor(private http: HttpClient, private fb: FormBuilder) {
  }

  ngOnInit() {
    this.fetchIndustries().then(result => {
      this.industries = result;

      for (let industry of this.industries) {
        this.processIndustry(industry, 0);
      }
    })

  }

  fetchIndustries() {
    return this.http.get<Industry[]>(this.apiURL + '/api/industry').toPromise();
  }

  onSubmit() {
    console.warn("OKAY")
    let profile = this.profileForm.value;
    profile.industryUuid = profile.industry.uuid;

    if (this.currentProfileUuid == undefined) {
      this.http.post<Profile>(this.apiURL + '/api/profile', JSON.stringify(profile), this.httpOptions).toPromise()
        .then(result => this.currentProfileUuid = result.uuid);
    } else {
      this.http.put<Profile>(this.apiURL + '/api/profile/' + this.currentProfileUuid, JSON.stringify(profile), this.httpOptions).toPromise()
        .then(result => this.currentProfileUuid = result.uuid);
    }
  }

  processIndustry(industry: Industry, level: number) {
    this.extractedIndustries.push({uuid: industry.uuid, name: '\u00a0'.repeat(level).concat(industry.name)})
    if (industry.children) {
      for (let child of industry.children) {
        this.processIndustry(child, level + 1)
      }
    }
  }
}
