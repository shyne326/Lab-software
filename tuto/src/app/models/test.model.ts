import { SampleType } from './sampleType.model';
import { Section } from './section.model';
import { Sample } from './sample.model';
import { Deserializable } from './deserializable.model';
import { ValeurDeReference } from './valeurdereference.model';

export interface Test{
   
    id: number;
    name: string;
    description?: string;
    unitOfMeasurement: string;
    price: number;
    prixSt: number;
    valeurDereference?: ValeurDeReference;
    sampleTypes?: SampleType[];
    section: Section;
    samples?: Sample[];
    active?: boolean;
    createdOn?: Date;
    updatedOn?: Date;
    type_resultat: string;


    // For posting
    sectionId?: number;


   /* deserialize(input: any): this {
        Object.assign(this, input);

        // Iterate over all cars for our user and map them to a proper `Car` model
         this.valeurDeReferences = input.valeurDeReferences.map(car => new ValeurDeReference().deserialize(car));
         this.sampleTypes = input.sampleTypes.map(car => new SampleType().deserialize(car));
         this.section = input.section.map(car => new Section().deserialize(car));
         this.samples = input.samples.map(car => new Sample().deserialize(car));
         this.panels = input.panels.map(car => new Panel().deserialize(car));

    return this;
    }
    */
}