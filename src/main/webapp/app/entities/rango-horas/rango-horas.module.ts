import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { InnapcitasSharedModule } from 'app/shared';
import {
    RangoHorasComponent,
    RangoHorasDetailComponent,
    RangoHorasUpdateComponent,
    RangoHorasDeletePopupComponent,
    RangoHorasDeleteDialogComponent,
    rangoHorasRoute,
    rangoHorasPopupRoute
} from './';

const ENTITY_STATES = [...rangoHorasRoute, ...rangoHorasPopupRoute];

@NgModule({
    imports: [InnapcitasSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RangoHorasComponent,
        RangoHorasDetailComponent,
        RangoHorasUpdateComponent,
        RangoHorasDeleteDialogComponent,
        RangoHorasDeletePopupComponent
    ],
    entryComponents: [RangoHorasComponent, RangoHorasUpdateComponent, RangoHorasDeleteDialogComponent, RangoHorasDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasRangoHorasModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
