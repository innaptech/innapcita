import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    TipoPagoComponent,
    TipoPagoDetailComponent,
    TipoPagoUpdateComponent,
    TipoPagoDeletePopupComponent,
    TipoPagoDeleteDialogComponent,
    tipoPagoRoute,
    tipoPagoPopupRoute
} from './';

const ENTITY_STATES = [...tipoPagoRoute, ...tipoPagoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoPagoComponent,
        TipoPagoDetailComponent,
        TipoPagoUpdateComponent,
        TipoPagoDeleteDialogComponent,
        TipoPagoDeletePopupComponent
    ],
    entryComponents: [TipoPagoComponent, TipoPagoUpdateComponent, TipoPagoDeleteDialogComponent, TipoPagoDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasTipoPagoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
