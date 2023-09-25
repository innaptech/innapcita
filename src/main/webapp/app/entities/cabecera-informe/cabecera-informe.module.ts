import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    CabeceraInformeComponent,
    CabeceraInformeDetailComponent,
    CabeceraInformeUpdateComponent,
    CabeceraInformeDeletePopupComponent,
    CabeceraInformeDeleteDialogComponent,
    cabeceraInformeRoute,
    cabeceraInformePopupRoute
} from './';

const ENTITY_STATES = [...cabeceraInformeRoute, ...cabeceraInformePopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        CabeceraInformeComponent,
        CabeceraInformeDetailComponent,
        CabeceraInformeUpdateComponent,
        CabeceraInformeDeleteDialogComponent,
        CabeceraInformeDeletePopupComponent
    ],
    entryComponents: [
        CabeceraInformeComponent,
        CabeceraInformeUpdateComponent,
        CabeceraInformeDeleteDialogComponent,
        CabeceraInformeDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasCabeceraInformeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
