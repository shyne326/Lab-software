import { NgModule, OnInit } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/master/dashboard/dashboard.component';
import { MasterComponent } from './components/master/master.component';
import { AdminMasterComponent } from './components/master/admin-master/admin-master.component';
import { CreateResultComponent } from './components/master/create-result/create-result.component';
import { CreateSampleComponent } from './components/master/create-sample/create-sample.component';
import { CreatePatientComponent } from './components/master/create-patient/create-patient.component';
import { ListPatientsComponent } from './components/master/list-patients/list-patients.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGuardService } from './services/auth-guard.service';
import { ValidationGuardService } from './services/validation-guard.service';
import { UniteComponent } from './components/master/unite/unite.component';
import { ResultIntermediateComponent } from './components/master/result-intermediate/result-intermediate.component';
import { ValidateResultsComponent } from './components/master/validate-results/validate-results.component';



const routes: Routes = [

    {path: 'login', component: LoginComponent},
    {path: 'logout', component: LogoutComponent, canActivate: [AuthGuardService]},
    
    {path:'', component:MasterComponent, canActivate: [AuthGuardService],

     children:[
       {path:'dashboard', component: DashboardComponent, canActivate: [AuthGuardService]},

       {path:'admin', component: AdminMasterComponent, canActivate: [AuthGuardService]
       // children:[ Will be added eventually
        //  {}
       // ]
       },
       {path: 'result-intermediate/sample/:sampleId', component:ResultIntermediateComponent},
       {path: 'results/create/sample/:sampleId', component: CreateResultComponent},
       {path: 'results/validate/sample/:sampleId', component: ValidateResultsComponent, canActivate:[ValidationGuardService]},
       {path: 'samples/create/patient/:patientId', component: CreateSampleComponent },  // 'id' represents the patient's since a sample must be attached to a patient
       {path: 'patients', component: ListPatientsComponent},
       {path: 'patients/create', component: CreatePatientComponent },
       {path: 'patients/edit/:id', component: CreatePatientComponent},
       {path: 'paillasse/:unitId', component: UniteComponent},
       {path:'samples', redirectTo:'dashboard', pathMatch:'full'},
       {path:'', redirectTo:'dashboard', pathMatch:'full'}   
      ]                
   }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

 
 }
