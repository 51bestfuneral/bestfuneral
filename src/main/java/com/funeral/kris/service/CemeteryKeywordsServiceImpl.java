package com.funeral.kris.service;

import com.funeral.kris.dao.TCemeteryKeywordsDAO;
import com.funeral.kris.model.TCemeteryKeywords;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CemeteryKeywordsServiceImpl
  implements CemeteryKeywordsService
{

  @Autowired
  private TCemeteryKeywordsDAO cemeteryKeywordsDAO;

  public void addResource(TCemeteryKeywords tCemeteryKeywords)
  {
    this.cemeteryKeywordsDAO.save(tCemeteryKeywords);
  }

  public void updateResource(TCemeteryKeywords tCemeteryKeywords)
  {
    this.cemeteryKeywordsDAO.save(tCemeteryKeywords);
  }

  public TCemeteryKeywords getResource(int id)
  {
    return (TCemeteryKeywords)this.cemeteryKeywordsDAO.findOne(Integer.valueOf(id));
  }

  public void deleteResource(int id)
  {
    this.cemeteryKeywordsDAO.delete(Integer.valueOf(id));
  }

  public List<TCemeteryKeywords> getResources()
  {
    List keywordsList = new ArrayList();
    Iterable keywordsIter = this.cemeteryKeywordsDAO.findAll();
    Iterator iter = keywordsIter.iterator();
    while (iter.hasNext()) {
      TCemeteryKeywords keyword = (TCemeteryKeywords)iter.next();
      keywordsList.add(keyword);
    }
    return keywordsList;
  }

  public List<TCemeteryKeywords> findByCemeteryId(int id)
  {
    return cemeteryKeywordsDAO.findByCemeteryId(id);
  }
}