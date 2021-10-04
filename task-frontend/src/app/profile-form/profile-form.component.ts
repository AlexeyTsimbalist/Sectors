import {Component, OnInit} from '@angular/core';
import {Industry} from "../model/industry";
import {FormBuilder, Validators} from "@angular/forms";
import {HttpService} from "../http/http.service";

@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.css']
})
export class ProfileFormComponent implements OnInit {

  extractedIndustries: { uuid: number, name: string }[] = [];
  currentProfileUuid: string | undefined;

  profileForm = this.fb.group({
    name: ['', Validators.required],
    industry: ['', Validators.required],
    agreement: ['', Validators.requiredTrue]
  });

  constructor(private httpService: HttpService, private fb: FormBuilder) {
  }

  ngOnInit() {
    this.httpService.fetchIndustries().then(industriesArray => {
      for (let industry of industriesArray) {
        this.processIndustry(industry, 0);
      }
    })

  }

  onSubmit() {
    let profile = this.profileForm.value;
    profile.industryUuid = profile.industry.uuid;

    if (this.currentProfileUuid == undefined) {
      this.httpService.saveProfile(profile)
        .then(result => this.currentProfileUuid = result.uuid);
    } else {
      this.httpService.updateProfile(this.currentProfileUuid, profile)
        .then(result => this.currentProfileUuid = result.uuid);
    }
  }

  processIndustry(industry: Industry, level: number) {
    this.extractedIndustries.push({uuid: industry.uuid, name: '\u00a0\u00a0'.repeat(level).concat(industry.name)})
    if (industry.children) {
      for (let child of industry.children) {
        this.processIndustry(child, level + 1)
      }
    }
  }
}
