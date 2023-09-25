import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PlantillaHistoriaMedicaAdultoComponent,
    PlantillaHistoriaMedicaAdultoDetailComponent,
    PlantillaHistoriaMedicaAdultoUpdateComponent,
    PlantillaHistoriaMedicaAdultoDeletePopupComponent,
    PlantillaHistoriaMedicaAdultoDeleteDialogComponent,
    plantillaHistoriaMedicaAdultoRoute,
    plantillaHistoriaMedicaAdultoPopupRoute
} from './';

const ENTITY_STATES = [...plantillaHistoriaMedicaAdultoRoute, ...plantillaHistoriaMedicaAdultoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        PlantillaHistoriaMedicaAdultoComponent,
        PlantillaHistoriaMedicaAdultoDetailComponent,
        PlantillaHistoriaMedicaAdultoUpdateComponent,
        PlantillaHistoriaMedicaAdultoDeleteDialogComponent,
        PlantillaHistoriaMedicaAdultoDeletePopupComponent
    ],
    entryComponents: [
        PlantillaHistoriaMedicaAdultoComponent,
        PlantillaHistoriaMedicaAdultoUpdateComponent,
        PlantillaHistoriaMedicaAdultoDeleteDialogComponent,
        PlantillaHistoriaMedicaAdultoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPlantillaHistoriaMedicaAdultoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
