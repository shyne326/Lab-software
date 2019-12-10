import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Patient } from 'src/app/models/patient.model';
import { PatientService } from 'src/app/services/patient.service';
import { ActivatedRoute, Router, NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-create-patient',
  templateUrl: './create-patient.component.html',
  styleUrls: ['./create-patient.component.scss']
})
export class CreatePatientComponent implements OnInit {

   patient: Patient;
   patientForm: FormGroup;

  successMessage: string = "";
  validationMessages = {

       'firstName': { 
                      'required':'Le champ Nom doit être rempli',
                      'minLength': 'Un nom doit avoir au moins 2 lettres',
                      'maxLength': 'Nom trop long'
                    },
       'lastName': { 
                      'required':'Le champ Prenom doit être rempli',
                      'minLength': 'Un Prenom doit avoir au moins 2 lettres',
                      'maxLength': 'Prenom trop long'
                    },
        'sex': { 
                     'required':'Veillez choisir le sexe du patient'
                    },
        'sigle': { 
                      'required':'Veillez choisir un sigle pour le patient'
                    },  
        'dateOfBirth': { 
                      'required':'Entrez la date de naissance du patient'
                    },
        'phone': { 
                      'required':'Entrez le numéro de téléphone du patient',
                      'minLength': 'Un numéro de téléphone doit avoir au moins 9 chiffres',
                      'maxLength': 'Numéro à plus de 14 chiffres n\'existe pas'
                    },
        'email': { 
                      'email':'Address email invalide',
                    },
        'address': { 
                      'required':'Entrez l\'addresse du patient',
                      'minLength': 'Trop court',
                      'maxLength': 'Trop long !'
                    },
       /* 'idCardNumber': { 
                      'required':'Entrez le numéro de cni du patient',
                      'minLength': 'Trop court. Numero CNI doit avoir au moins 9 chiffres',
                      'maxLength': 'Numéro à plus de 14 chiffres n\'existe pas'
                    },
        */                  
  };
    formErrors = {

    };

  constructor(private fb: FormBuilder, private patientService: PatientService, private route: ActivatedRoute, private _router: Router) { }

  ngOnInit() {

    this.patientForm = this.fb.group({

       firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]],
       lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]],
       sex: ['', Validators.required],
       sigle: ['', Validators.required],
       dob: ['', Validators.required],
       phone: ['', Validators.required],
       cni: [''],
       email: [''],
       address: [''],
       nationality: ['']
    });

    this.route.paramMap.subscribe(params => {
      const id = +params.get('id');
      if(id){
          this.patientService.getPatientById(id).subscribe(
               (data: Patient) =>{
                   this.editPatient(data);
               },
               err => console.log(err)
          );
      }
      else{

        this.patient = {
             
             id: null,
             firstName : '',
             lastName : '',
             dob: null,
             nationality: '',
             address: '',
             phone: '',
             email: '',
           //  code_utilisateur: '',
             sex: null,
             sigle: null,
             cni: '',
             samples: null
           //  photo: '',
           
        }
      }
    });
  }

  public editPatient(patient: Patient){

    this.patientForm.patchValue({

      id: patient.id,
      firstName : patient.firstName,
      lastName : patient.lastName,
      dob: patient.dob,
      nationality: patient.nationality,
      address: patient.address,
      phone: patient.phone,
      email: patient.email,
      codeUtilisateur: patient.codeUtilisateur,
      sex: patient.sex,
      sigle: patient.sigle,
      cni: patient.cni,
      samples: patient.samples
    //  photo: '',
    });
              
 }

  onSubmit(){

    let extras: NavigationExtras = {
      state:{
        message: "Patient créer avec succès !"
      }
    }
    
    //  this.patient = this.patientForm.value;
      this.mapFormValuesToPatientModel();
      console.log(this.patient);
      this.patientService.addPatient(this.patient).subscribe(

        () => this._router.navigate(['/patients'], extras),
    
        (err: any) => console.log(err)
      );

  }

  mapFormValuesToPatientModel(){

     this.patient.id = null;
     this.patient.firstName = this.patientForm.value.firstName;
     this.patient.lastName = this.patientForm.value.lastName;
     this.patient.cni = this.patientForm.value.cni;
     this.patient.nationality = this.patientForm.value.nationality;
     this.patient.phone = this.patientForm.value.phone;
    // this.patient.samples = null;
     this.patient.sex = this.patientForm.value.sex; //this.patient.sex.id = +this.patient.sex.id;  // This is not working don't know why ??
     this.patient.sigle =  this.patientForm.value.sigle; //this.patient.sigle.id = +this.patient.sigle.id; //This too has the same problem
     this.patient.dob = this.patientForm.value.dob;
     this.patient.email = this.patientForm.value.email;
     this.patient.address = this.patientForm.value.address;
     
  }
}
