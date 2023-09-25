import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    BitacoraComponent,
    BitacoraDetailComponent,
    BitacoraUpdateComponent,
    BitacoraDeletePopupComponent,
    BitacoraDeleteDialogComponent,
    bitacoraRoute,
    bitacoraPopupRoute
} from './';

const ENTITY_STATES = [...bitacoraRoute, ...bitacoraPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BitacoraComponent,
        BitacoraDetailComponent,
        BitacoraUpdateComponent,
        BitacoraDeleteDialogComponent,
        BitacoraDeletePopupComponent
    ],
    entryComponents: [BitacoraComponent, BitacoraUpdateComponent, BitacoraDeleteDialogComponent, BitacoraDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasBitacoraModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
