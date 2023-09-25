import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PlantillaHistoriaPersonalAdultoComponent,
    PlantillaHistoriaPersonalAdultoDetailComponent,
    PlantillaHistoriaPersonalAdultoUpdateComponent,
    PlantillaHistoriaPersonalAdultoDeletePopupComponent,
    PlantillaHistoriaPersonalAdultoDeleteDialogComponent,
    plantillaHistoriaPersonalAdultoRoute,
    plantillaHistoriaPersonalAdultoPopupRoute
} from './';

const ENTITY_STATES = [...plantillaHistoriaPersonalAdultoRoute, ...plantillaHistoriaPersonalAdultoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        PlantillaHistoriaPersonalAdultoComponent,
        PlantillaHistoriaPersonalAdultoDetailComponent,
        PlantillaHistoriaPersonalAdultoUpdateComponent,
        PlantillaHistoriaPersonalAdultoDeleteDialogComponent,
        PlantillaHistoriaPersonalAdultoDeletePopupComponent
    ],
    entryComponents: [
        PlantillaHistoriaPersonalAdultoComponent,
        PlantillaHistoriaPersonalAdultoUpdateComponent,
        PlantillaHistoriaPersonalAdultoDeleteDialogComponent,
        PlantillaHistoriaPersonalAdultoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPlantillaHistoriaPersonalAdultoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
