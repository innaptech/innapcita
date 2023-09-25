import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PlantillaHistoriaPersonalNinoComponent,
    PlantillaHistoriaPersonalNinoDetailComponent,
    PlantillaHistoriaPersonalNinoUpdateComponent,
    PlantillaHistoriaPersonalNinoDeletePopupComponent,
    PlantillaHistoriaPersonalNinoDeleteDialogComponent,
    plantillaHistoriaPersonalNinoRoute,
    plantillaHistoriaPersonalNinoPopupRoute
} from './';

const ENTITY_STATES = [...plantillaHistoriaPersonalNinoRoute, ...plantillaHistoriaPersonalNinoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        PlantillaHistoriaPersonalNinoComponent,
        PlantillaHistoriaPersonalNinoDetailComponent,
        PlantillaHistoriaPersonalNinoUpdateComponent,
        PlantillaHistoriaPersonalNinoDeleteDialogComponent,
        PlantillaHistoriaPersonalNinoDeletePopupComponent
    ],
    entryComponents: [
        PlantillaHistoriaPersonalNinoComponent,
        PlantillaHistoriaPersonalNinoUpdateComponent,
        PlantillaHistoriaPersonalNinoDeleteDialogComponent,
        PlantillaHistoriaPersonalNinoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPlantillaHistoriaPersonalNinoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
