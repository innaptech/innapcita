import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    ReferenciaMedicaComponent,
    ReferenciaMedicaDetailComponent,
    ReferenciaMedicaUpdateComponent,
    ReferenciaMedicaDeletePopupComponent,
    ReferenciaMedicaDeleteDialogComponent,
    referenciaMedicaRoute,
    referenciaMedicaPopupRoute
} from './';

const ENTITY_STATES = [...referenciaMedicaRoute, ...referenciaMedicaPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReferenciaMedicaComponent,
        ReferenciaMedicaDetailComponent,
        ReferenciaMedicaUpdateComponent,
        ReferenciaMedicaDeleteDialogComponent,
        ReferenciaMedicaDeletePopupComponent
    ],
    entryComponents: [
        ReferenciaMedicaComponent,
        ReferenciaMedicaUpdateComponent,
        ReferenciaMedicaDeleteDialogComponent,
        ReferenciaMedicaDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasReferenciaMedicaModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
