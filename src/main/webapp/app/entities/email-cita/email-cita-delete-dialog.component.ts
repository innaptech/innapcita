import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmailCita } from 'app/shared/model/email-cita.model';
import { EmailCitaService } from './email-cita.service';

@Component({
    selector: 'jhi-email-cita-delete-dialog',
    templateUrl: './email-cita-delete-dialog.component.html'
})
export class EmailCitaDeleteDialogComponent {
    emailCita: IEmailCita;

    constructor(
        protected emailCitaService: EmailCitaService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.emailCitaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'emailCitaListModification',
                content: 'Deleted an emailCita'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-email-cita-delete-popup',
    template: ''
})
export class EmailCitaDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ emailCita }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EmailCitaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.emailCita = emailCita;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/email-cita', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/email-cita', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
