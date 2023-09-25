import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    EstatusPagoComponent,
    EstatusPagoDetailComponent,
    EstatusPagoUpdateComponent,
    EstatusPagoDeletePopupComponent,
    EstatusPagoDeleteDialogComponent,
    estatusPagoRoute,
    estatusPagoPopupRoute
} from './';

const ENTITY_STATES = [...estatusPagoRoute, ...estatusPagoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EstatusPagoComponent,
        EstatusPagoDetailComponent,
        EstatusPagoUpdateComponent,
        EstatusPagoDeleteDialogComponent,
        EstatusPagoDeletePopupComponent
    ],
    entryComponents: [EstatusPagoComponent, EstatusPagoUpdateComponent, EstatusPagoDeleteDialogComponent, EstatusPagoDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEstatusPagoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
