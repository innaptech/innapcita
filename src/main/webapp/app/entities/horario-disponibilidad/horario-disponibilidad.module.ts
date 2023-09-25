import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

import { InnapcitasSharedModule } from 'app/shared';
import {
    HorarioDisponibilidadComponent,
    HorarioDisponibilidadMedicoComponent,
    HorarioDisponibilidadDetailComponent,
    HorarioDisponibilidadUpdateComponent,
    HorarioDisponibilidadDeletePopupComponent,
    HorarioDisponibilidadDeleteDialogComponent,
    horarioDisponibilidadRoute,
    horarioDisponibilidadPopupRoute
} from './';

const ENTITY_STATES = [...horarioDisponibilidadRoute, ...horarioDisponibilidadPopupRoute];

@NgModule({
    imports: [
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        }),
        InnapcitasSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        HorarioDisponibilidadComponent,
        HorarioDisponibilidadMedicoComponent,
        HorarioDisponibilidadDetailComponent,
        HorarioDisponibilidadUpdateComponent,
        HorarioDisponibilidadDeleteDialogComponent,
        HorarioDisponibilidadDeletePopupComponent
    ],
    entryComponents: [
        HorarioDisponibilidadComponent,
        HorarioDisponibilidadMedicoComponent,
        HorarioDisponibilidadUpdateComponent,
        HorarioDisponibilidadDeleteDialogComponent,
        HorarioDisponibilidadDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasHorarioDisponibilidadModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
