import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PieDePaginaInformeComponent,
    PieDePaginaInformeDetailComponent,
    PieDePaginaInformeUpdateComponent,
    PieDePaginaInformeDeletePopupComponent,
    PieDePaginaInformeDeleteDialogComponent,
    pieDePaginaInformeRoute,
    pieDePaginaInformePopupRoute
} from './';

const ENTITY_STATES = [...pieDePaginaInformeRoute, ...pieDePaginaInformePopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        PieDePaginaInformeComponent,
        PieDePaginaInformeDetailComponent,
        PieDePaginaInformeUpdateComponent,
        PieDePaginaInformeDeleteDialogComponent,
        PieDePaginaInformeDeletePopupComponent
    ],
    entryComponents: [
        PieDePaginaInformeComponent,
        PieDePaginaInformeUpdateComponent,
        PieDePaginaInformeDeleteDialogComponent,
        PieDePaginaInformeDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPieDePaginaInformeModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
