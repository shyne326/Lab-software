import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SampleService } from 'src/app/services/sample.service';
import { Sample } from 'src/app/models/sample.model';
import { Section } from 'src/app/models/section.model';

@Component({
  selector: 'app-result-intermediate',
  templateUrl: './result-intermediate.component.html',
  styleUrls: ['./result-intermediate.component.scss']
})
export class ResultIntermediateComponent implements OnInit {

  sample: Sample;
  sections: Section[];

  constructor(private _router: Router, private route: ActivatedRoute, private sampleService: SampleService) { this.sections = [];}

  ngOnInit() {

    this.route.paramMap.subscribe((params) => {

      const samp = params.get('sampleId');

      if(samp){

        this.sampleService.getSampleById(+samp).subscribe(
          (response: Sample) => {this.sample=response; console.log(this.sample)},

          err => console.log(err)
        );
      }

    });

    this.listSections();
  }

  public listSections(){

    console.log(this.sample.tests);
    for(const t of this.sample.tests){

      this.sections.push(t.section);
    }
    console.log(this.sections);

    // Eliminating duplicates

    const set = new Set(this.sections);
    this.sections = [...set];
    console.log(this.sections);

  }

}
