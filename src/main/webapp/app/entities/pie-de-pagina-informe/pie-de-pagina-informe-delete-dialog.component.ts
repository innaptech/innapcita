import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPieDePaginaInforme } from 'app/shared/model/pie-de-pagina-informe.model';
import { PieDePaginaInformeService } from './pie-de-pagina-informe.service';

@Component({
    selector: 'jhi-pie-de-pagina-informe-delete-dialog',
    templateUrl: './pie-de-pagina-informe-delete-dialog.component.html'
})
export class PieDePaginaInformeDeleteDialogComponent {
    pieDePaginaInforme: IPieDePaginaInforme;

    constructor(
        protected pieDePaginaInformeService: PieDePaginaInformeService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pieDePaginaInformeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pieDePaginaInformeListModification',
                content: 'Deleted an pieDePaginaInforme'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pie-de-pagina-informe-delete-popup',
    template: ''
})
export class PieDePaginaInformeDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pieDePaginaInforme }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PieDePaginaInformeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pieDePaginaInforme = pieDePaginaInforme;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/pie-de-pagina-informe', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/pie-de-pagina-informe', { outlets: { popup: null } }]);
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
