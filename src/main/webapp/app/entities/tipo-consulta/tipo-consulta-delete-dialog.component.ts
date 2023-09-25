import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoConsulta } from 'app/shared/model/tipo-consulta.model';
import { TipoConsultaService } from './tipo-consulta.service';

@Component({
    selector: 'jhi-tipo-consulta-delete-dialog',
    templateUrl: './tipo-consulta-delete-dialog.component.html'
})
export class TipoConsultaDeleteDialogComponent {
    tipoConsulta: ITipoConsulta;

    constructor(
        protected tipoConsultaService: TipoConsultaService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tipoConsultaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tipoConsultaListModification',
                content: 'Deleted an tipoConsulta'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tipo-consulta-delete-popup',
    template: ''
})
export class TipoConsultaDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tipoConsulta }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TipoConsultaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tipoConsulta = tipoConsulta;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/tipo-consulta', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/tipo-consulta', { outlets: { popup: null } }]);
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
