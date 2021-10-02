import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Sector} from "./sector";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

  sectors: Sector[] = [];
  extractedSectors: { id: number, name: string, level: number }[] = [];
  selectedSector: Sector | undefined;

  constructor(private http: HttpClient) {
  }

  log(x:any) {
    console.log(x);
  }

  ngOnInit() {
    this.fetchSectors().then(result => {
      this.sectors = result;

      for (let sector of this.sectors) {
        console.warn('aaa')
        this.processSector(sector, 0);
      }
    })

  }

  fetchSectors() {
    return this.http.get<Sector[]>('assets/sectors.json').toPromise();
  }

  sendForm() {
    console.log("SENDING")
  }

  processSector(sector: Sector, level: number) {
    this.extractedSectors.push({id: sector.id, name: '\u00a0'.repeat(level).concat(sector.name), level: level})
    if (sector.children) {
      for (let child of sector.children) {
        this.processSector(child, level + 1)
      }
    }
  }
}
