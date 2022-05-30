package br.inatel.InternetProviderBrowser.service;

import java.util.List;

public interface ServiceModel<Entity,EntityId> {
	public List<Entity> list();
	public Entity find(EntityId id);
	public Entity insert(Entity entity);
	public Entity update(EntityId id, Entity entity);
	public void delete(EntityId id);
}
