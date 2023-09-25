import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    EstatusCitaComponent,
    EstatusCitaDetailComponent,
    EstatusCitaUpdateComponent,
    EstatusCitaDeletePopupComponent,
    EstatusCitaDeleteDialogComponent,
    estatusCitaRoute,
    estatusCitaPopupRoute
} from './';

const ENTITY_STATES = [...estatusCitaRoute, ...estatusCitaPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EstatusCitaComponent,
        EstatusCitaDetailComponent,
        EstatusCitaUpdateComponent,
        EstatusCitaDeleteDialogComponent,
        EstatusCitaDeletePopupComponent
    ],
    entryComponents: [EstatusCitaComponent, EstatusCitaUpdateComponent, EstatusCitaDeleteDialogComponent, EstatusCitaDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEstatusCitaModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
