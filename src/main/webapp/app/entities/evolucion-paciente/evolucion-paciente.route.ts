import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';
import { EvolucionPacienteService } from './evolucion-paciente.service';
import { EvolucionPacienteComponent } from './evolucion-paciente.component';
import { EvolucionPacienteDetailComponent } from './evolucion-paciente-detail.component';
import { EvolucionPacienteUpdateComponent } from './evolucion-paciente-update.component';
import { EvolucionPacienteDeletePopupComponent } from './evolucion-paciente-delete-dialog.component';
import { IEvolucionPaciente } from 'app/shared/model/evolucion-paciente.model';

@Injectable({ providedIn: 'root' })
export class EvolucionPacienteResolve implements Resolve<IEvolucionPaciente> {
    constructor(private service: EvolucionPacienteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEvolucionPaciente> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EvolucionPaciente>) => response.ok),
                map((evolucionPaciente: HttpResponse<EvolucionPaciente>) => evolucionPaciente.body)
            );
        }
        return of(new EvolucionPaciente());
    }
}

export const evolucionPacienteRoute: Routes = [
    {
        path: '',
        component: EvolucionPacienteComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innapcitasApp.evolucionPaciente.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: EvolucionPacienteDetailComponent,
        resolve: {
            evolucionPaciente: EvolucionPacienteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.evolucionPaciente.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: EvolucionPacienteUpdateComponent,
        resolve: {
            evolucionPaciente: EvolucionPacienteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.evolucionPaciente.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: EvolucionPacienteUpdateComponent,
        resolve: {
            evolucionPaciente: EvolucionPacienteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.evolucionPaciente.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const evolucionPacientePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: EvolucionPacienteDeletePopupComponent,
        resolve: {
            evolucionPaciente: EvolucionPacienteResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innapcitasApp.evolucionPaciente.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
