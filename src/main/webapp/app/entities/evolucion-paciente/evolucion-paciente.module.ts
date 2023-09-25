import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { EditorModule } from '@tinymce/tinymce-angular';
import { SpeechRecognitionModule, RxSpeechRecognitionService } from '@kamiazya/ngx-speech-recognition';

import { DlDateTimeDateModule, DlDateTimePickerModule } from 'angular-bootstrap-datetimepicker';

import { InnapcitasSharedModule } from 'app/shared';
import {
    EvolucionPacienteComponent,
    EvolucionPacienteDetailComponent,
    EvolucionPacienteUpdateComponent,
    EvolucionPacienteDeletePopupComponent,
    EvolucionPacienteDeleteDialogComponent,
    evolucionPacienteRoute,
    evolucionPacientePopupRoute
} from './';

const ENTITY_STATES = [...evolucionPacienteRoute, ...evolucionPacientePopupRoute];

@NgModule({
    imports: [
        InnapcitasSharedModule,
        RouterModule.forChild(ENTITY_STATES),
        DlDateTimeDateModule,
        DlDateTimePickerModule,
        EditorModule,
        SpeechRecognitionModule.withConfig({
          lang: 'es-VE',
          interimResults: true,
          maxAlternatives: 10,
        })],
    declarations: [
        EvolucionPacienteComponent,
        EvolucionPacienteDetailComponent,
        EvolucionPacienteUpdateComponent,
        EvolucionPacienteDeleteDialogComponent,
        EvolucionPacienteDeletePopupComponent
    ],
    entryComponents: [
        EvolucionPacienteComponent,
        EvolucionPacienteUpdateComponent,
        EvolucionPacienteDeleteDialogComponent,
        EvolucionPacienteDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }, RxSpeechRecognitionService],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasEvolucionPacienteModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
