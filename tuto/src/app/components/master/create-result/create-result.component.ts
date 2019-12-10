import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormArray, FormGroup, FormControl } from '@angular/forms';
import { Sample } from 'src/app/models/sample.model';
import { Result } from 'src/app/models/result.model';
import { SampleService } from 'src/app/services/sample.service';
import { ResultService } from 'src/app/services/result.service';
import { DeviceService } from 'src/app/services/device.service';
import { Device } from 'src/app/models/device.model';

@Component({
  selector: 'app-create-result',
  templateUrl: './create-result.component.html',
  styleUrls: ['./create-result.component.scss']
})
export class CreateResultComponent implements OnInit {

  form: FormGroup;
  sample: Sample = null;
  results: Result[] = [];

  today = new Date().toDateString();
  devices: Device[] = []; 

  count: number = 0;


  constructor(private fb:FormBuilder, private route:ActivatedRoute, 
                 private sampleService: SampleService, private resultService: ResultService,
                  private deviceService: DeviceService,
                   private _router: Router) { }

  ngOnInit() {


    this.route.paramMap.subscribe((params) => {

      const samp = params.get('sampleId'); console.log(samp);

      if(samp){

        this.sampleService.getSampleById(+samp).subscribe(
          (response:Sample) => {this.sample = response;
                                console.log(this.sample)
                               },
          (err) =>  console.log(err)
        );

      }

      this.deviceService.getDevices().subscribe(
        (response: Device[]) => {this.devices = response; console.log(response)},
        err =>console.log(err)
      );

    });


    this.form = this.fb.group({
      testsArray: this.fb.array([])
    });
    

  }

  ngDoCheck(){
    console.log("Hello Hello ... "+this.count);
    if(this.count<3){
      this.addTestInputs();
      this.count++;
    }   
  }

  public addTestInputs(){
    if(this.sample.results.length <= 0){
      console.log("This is in the addTests method called from ngDoCheck " + this.sample);
   // (<FormArray>this.form.get('testsArray')).clear();
    this.sample.tests.forEach((o, i) => {
      const group = this.fb.group({

          resultat:[],
          attachedFile:[''],
          testId:[o.id],
          sampleId:[this.sample.id],
          validated: [false],
          validatorId:[1],
          device:['']
      });
      (this.form.controls.testsArray as FormArray).push(group);
    });
  }

  else{
 
        this.form.patchValue({

          resultat : [''] //this.sample.  //??? // Don't know yet what to do
        });

   }

  }
  
  public onSubmit(){

    this.mapFormValuesToResultModel();
    this.resultService.addResult(this.results).subscribe(
      () => this._router.navigate(['/dashboard']),
      err => console.log(err)
    );
    
  }

  public mapFormValuesToResultModel(){

    let i=0;
    for(i=0; i<this.sample.tests.length; i++){
      console.log(this.sample.tests.length);
      this.results[i] = {

        id : null,
        resultat: null,
        validated: false,
        validator: null,
        device: null,
        reactif: null,
        sample: null,
        test: null,
        testId: null,
        sampleId: null,
        validatorId: null
     }
    } 
       
    this.results = this.form.value.testsArray
    .map((v, j) => this.results[j] = v)
    //.filter(v => v !== null))
     console.log(this.form.value.testsArray);
  }

}
