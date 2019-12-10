import { Sample } from './sample.model';
import { User } from './user.model';
import { Deserializable } from './deserializable.model';

export interface Patient extends User{
  

    samples: Sample[];
    /*
    deserialize(input: any){
        Object.assign(this, input);
        this.samples = input.samples.map(s => new Sample().deserialize(s));

        return this;
    } 
    */
}