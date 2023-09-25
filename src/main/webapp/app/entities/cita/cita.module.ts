import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { AutocompleteLibModule } from 'angular-ng-autocomplete';
import { NgAddToCalendarModule } from '@trademe/ng-add-to-calendar';

import { DlDateTimeDateModule, DlDateTimePickerModule } from 'angular-bootstrap-datetimepicker';

import { InnapcitasSharedModule } from 'app/shared';
import {
    CitaComponent,
    CitaUserComponent,
    CitaPacienteComponent,
    CitaPacienteDetailComponent,
    CitaDetailComponent,
    CitaUpdateComponent,
    CitaDeletePopupComponent,
    CitaDeleteDialogComponent,
    citaRoute,
    citaPopupRoute
} from './';

const ENTITY_STATES = [...citaRoute, ...citaPopupRoute];

@NgModule({
    imports: [
        InnapcitasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        AutocompleteLibModule,
        DlDateTimeDateModule,
        DlDateTimePickerModule,
        NgAddToCalendarModule
    ],
    declarations: [
        CitaComponent,
        CitaUserComponent,
        CitaPacienteComponent,
        CitaPacienteDetailComponent,
        CitaDetailComponent,
        CitaUpdateComponent,
        CitaDeleteDialogComponent,
        CitaDeletePopupComponent
    ],
    entryComponents: [
        CitaComponent,
        CitaUserComponent,
        CitaPacienteComponent,
        CitaPacienteDetailComponent,
        CitaUpdateComponent,
        CitaDeleteDialogComponent,
        CitaDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasCitaModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
