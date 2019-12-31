import { Employee } from './employee.model';
import { Patient } from './patient.model';
import { SampleType } from './sampleType.model';
import { Test } from './test.model';
import { Result } from './result.model';

export interface Sample{

    id: number;
    patient?: Patient;
    labTechnician?: Employee;
    sampleType?: SampleType;
    tests?: Test[];
    //results?: Result[];
    results?: Result
    requester?: string;
    note: string;
  //  requesterAddress: string;
    createdOn?: Date;
    updatedOn?: Date;

 // For posting

    testIds ?: number[];
    labTechnicianId?: number;
    patientId?: number;
    sampleTypeId?: number;

  
    
    /*deserialize(input: any){
        Object.assign(this, input);
        this.labTechnician = input.labTechnician.map(lt => new Employee().deserialize(lt));
        this.sampleType = input.sampleTypes.map(car => new SampleType().deserialize(car));

        return this;
    }
    */
}