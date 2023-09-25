import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHorarioDisponibilidad } from 'app/shared/model/horario-disponibilidad.model';
import { HorarioDisponibilidadService } from './horario-disponibilidad.service';

@Component({
    selector: 'jhi-horario-disponibilidad-delete-dialog',
    templateUrl: './horario-disponibilidad-delete-dialog.component.html'
})
export class HorarioDisponibilidadDeleteDialogComponent {
    horarioDisponibilidad: IHorarioDisponibilidad;

    constructor(
        protected horarioDisponibilidadService: HorarioDisponibilidadService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.horarioDisponibilidadService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'horarioDisponibilidadListModification',
                content: 'Deleted an horarioDisponibilidad'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-horario-disponibilidad-delete-popup',
    template: ''
})
export class HorarioDisponibilidadDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ horarioDisponibilidad }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HorarioDisponibilidadDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.horarioDisponibilidad = horarioDisponibilidad;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/horario-disponibilidad', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/horario-disponibilidad', { outlets: { popup: null } }]);
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
