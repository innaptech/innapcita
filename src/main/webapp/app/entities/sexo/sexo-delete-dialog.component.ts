import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISexo } from 'app/shared/model/sexo.model';
import { SexoService } from './sexo.service';

@Component({
    selector: 'jhi-sexo-delete-dialog',
    templateUrl: './sexo-delete-dialog.component.html'
})
export class SexoDeleteDialogComponent {
    sexo: ISexo;

    constructor(protected sexoService: SexoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sexoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sexoListModification',
                content: 'Deleted an sexo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sexo-delete-popup',
    template: ''
})
export class SexoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sexo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SexoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sexo = sexo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/sexo', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/sexo', { outlets: { popup: null } }]);
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
