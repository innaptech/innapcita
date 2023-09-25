import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    MonedaCambioComponent,
    MonedaCambioDetailComponent,
    MonedaCambioUpdateComponent,
    MonedaCambioDeletePopupComponent,
    MonedaCambioDeleteDialogComponent,
    monedaCambioRoute,
    monedaCambioPopupRoute
} from './';

const ENTITY_STATES = [...monedaCambioRoute, ...monedaCambioPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MonedaCambioComponent,
        MonedaCambioDetailComponent,
        MonedaCambioUpdateComponent,
        MonedaCambioDeleteDialogComponent,
        MonedaCambioDeletePopupComponent
    ],
    entryComponents: [
        MonedaCambioComponent,
        MonedaCambioUpdateComponent,
        MonedaCambioDeleteDialogComponent,
        MonedaCambioDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasMonedaCambioModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
