package com.guaire.innapcitas.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.guaire.innapcitas.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Especialidad.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.EspecialidadMedico.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Cita.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.DiaSemana.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.EstatusPago.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.HorarioDisponibilidad.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.RangoHoras.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.TipoPago.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Pago.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.TipoConsulta.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.MonedaCambio.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Sexo.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.ReferenciaMedica.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.EstadoCivil.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.HistoriaPersonalAdulto.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.GradoInstruccion.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.HistoriaPersonalNino.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.HistoriaMedicaAdulto.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.HistoriaMedicaNino.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Cita.class.getName() + ".historiaPersonalAdultos", jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Cita.class.getName() + ".historiaMedicaAdultos", jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Cita.class.getName() + ".historiaPersonalNinos", jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Cita.class.getName() + ".historiaMedicaNinos", jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PlantillaHistoriaPersonalAdulto.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PlantillaHistoriaPersonalNino.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PlantillaHistoriaMedicaAdulto.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PlantillaHistoriaMedicaNino.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.EstatusCita.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.EmailCita.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.EvolucionPaciente.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.ExamenComplementario.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PlantillaInforme.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PlantillaRecipe.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Bitacora.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.Perfil.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.CabeceraInforme.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PieDePaginaInforme.class.getName(), jcacheConfiguration);
            cm.createCache(com.guaire.innapcitas.domain.PlantillaAntecedentes.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
