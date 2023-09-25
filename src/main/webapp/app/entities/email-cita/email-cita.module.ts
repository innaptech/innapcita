import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';

import { InnapcitasSharedModule } from 'app/shared';
import {
    EmailCitaComponent,
    EmailCitaDetailComponent,
    EmailCitaUpdateComponent,
    EmailCitaDeletePopupComponent,
    EmailCitaDeleteDialogComponent,
    emailCitaRoute,
    emailCitaPopupRoute
} from './';

const ENTITY_STATES = [...emailCitaRoute, ...emailCitaPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES), EditorModule],
    declarations: [
        EmailCitaComponent,
        EmailCitaDetailComponent,
        EmailCitaUpdateComponent,
        EmailCitaDeleteDialogComponent,
        EmailCitaDeletePopupComponent
    ],
    entryComponents: [EmailCitaComponent, EmailCitaUpdateComponent, EmailCitaDeleteDialogComponent, EmailCitaDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEmailCitaModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
