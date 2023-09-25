import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PlantillaHistoriaMedicaNinoComponent,
    PlantillaHistoriaMedicaNinoDetailComponent,
    PlantillaHistoriaMedicaNinoUpdateComponent,
    PlantillaHistoriaMedicaNinoDeletePopupComponent,
    PlantillaHistoriaMedicaNinoDeleteDialogComponent,
    plantillaHistoriaMedicaNinoRoute,
    plantillaHistoriaMedicaNinoPopupRoute
} from './';

const ENTITY_STATES = [...plantillaHistoriaMedicaNinoRoute, ...plantillaHistoriaMedicaNinoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        PlantillaHistoriaMedicaNinoComponent,
        PlantillaHistoriaMedicaNinoDetailComponent,
        PlantillaHistoriaMedicaNinoUpdateComponent,
        PlantillaHistoriaMedicaNinoDeleteDialogComponent,
        PlantillaHistoriaMedicaNinoDeletePopupComponent
    ],
    entryComponents: [
        PlantillaHistoriaMedicaNinoComponent,
        PlantillaHistoriaMedicaNinoUpdateComponent,
        PlantillaHistoriaMedicaNinoDeleteDialogComponent,
        PlantillaHistoriaMedicaNinoDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPlantillaHistoriaMedicaNinoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
