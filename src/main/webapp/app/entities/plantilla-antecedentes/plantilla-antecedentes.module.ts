import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PlantillaAntecedentesComponent,
    PlantillaAntecedentesDetailComponent,
    PlantillaAntecedentesUpdateComponent,
    PlantillaAntecedentesDeletePopupComponent,
    PlantillaAntecedentesDeleteDialogComponent,
    plantillaAntecedentesRoute,
    plantillaAntecedentesPopupRoute
} from './';

const ENTITY_STATES = [...plantillaAntecedentesRoute, ...plantillaAntecedentesPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        PlantillaAntecedentesComponent,
        PlantillaAntecedentesDetailComponent,
        PlantillaAntecedentesUpdateComponent,
        PlantillaAntecedentesDeleteDialogComponent,
        PlantillaAntecedentesDeletePopupComponent
    ],
    entryComponents: [
        PlantillaAntecedentesComponent,
        PlantillaAntecedentesUpdateComponent,
        PlantillaAntecedentesDeleteDialogComponent,
        PlantillaAntecedentesDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPlantillaAntecedentesModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
