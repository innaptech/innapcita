import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    PagoComponent,
    PagoDetailComponent,
    PagoUpdateComponent,
    PagoDeletePopupComponent,
    PagoDeleteDialogComponent,
    pagoRoute,
    pagoPopupRoute
} from './';

const ENTITY_STATES = [...pagoRoute, ...pagoPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [PagoComponent, PagoDetailComponent, PagoUpdateComponent, PagoDeleteDialogComponent, PagoDeletePopupComponent],
    entryComponents: [PagoComponent, PagoUpdateComponent, PagoDeleteDialogComponent, PagoDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasPagoModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
