import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import {
    InnapcitasSharedLibsModule,
    InnapcitasSharedCommonModule,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective,
    HasNotAuthorityDirective
} from './';

@NgModule({
    imports: [InnapcitasSharedLibsModule, InnapcitasSharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective, HasNotAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent],
    exports: [InnapcitasSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective, HasNotAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnapcitasSharedModule {
    static forRoot() {
        return {
            ngModule: InnapcitasSharedModule
        };
    }
}
