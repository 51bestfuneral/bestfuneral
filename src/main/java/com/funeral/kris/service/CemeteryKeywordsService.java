package com.funeral.kris.service;

import com.funeral.kris.model.TCemeteryKeywords;
import java.util.List;

public abstract interface CemeteryKeywordsService
{
  public abstract void addResource(TCemeteryKeywords paramTCemeteryKeywords);

  public abstract void updateResource(TCemeteryKeywords paramTCemeteryKeywords);

  public abstract TCemeteryKeywords getResource(int paramInt);

  public abstract void deleteResource(int paramInt);

  public abstract List<TCemeteryKeywords> getResources();

  public abstract List<TCemeteryKeywords> findByCemeteryId(int paramInt);
}