import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { MentionModule } from 'angular-mentions';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PlantillaInformeComponent,
    PlantillaInformeDetailComponent,
    PlantillaInformeUpdateComponent,
    PlantillaInformeDeletePopupComponent,
    PlantillaInformeDeleteDialogComponent,
    plantillaInformeRoute,
    plantillaInformePopupRoute
} from './';

const ENTITY_STATES = [...plantillaInformeRoute, ...plantillaInformePopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule, MentionModule],
    declarations: [
        PlantillaInformeComponent,
        PlantillaInformeDetailComponent,
        PlantillaInformeUpdateComponent,
        PlantillaInformeDeleteDialogComponent,
        PlantillaInformeDeletePopupComponent
    ],
    entryComponents: [
        PlantillaInformeComponent,
        PlantillaInformeUpdateComponent,
        PlantillaInformeDeleteDialogComponent,
        PlantillaInformeDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPlantillaInformeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
