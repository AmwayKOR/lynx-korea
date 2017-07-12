package com.amway.core.dao.impl;

import static org.junit.Assert.assertTrue;

import com.amway.core.account.dao.AmwayAccountDao;
import com.amway.core.model.AmwayAccountModel;
import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.impex.jalo.ImpExException;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;

import java.util.List;

import javax.annotation.Resource;
import org.junit.Before;

import org.junit.Test;
import org.mockito.MockitoAnnotations;

/**
 * Created by aiueq92 on 4/11/16.
 */
@IntegrationTest
public class DefaultAmwayAccountDaoIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private AmwayAccountDao amwayAccountDao;

    private static String NO_ACCOUNTS = "none";
    private static String ALL_ACCOUNTS = "n";

    @Before
    public void prepare()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void setUp() throws ImpExException
    {
        importCsv("/amwaycore/test/common.csv", "windows-1252");
        importCsv("/amwaycore/test/accountDaoTestData.csv", "windows-1252");
    }

    @Test
    public void amwayAccountDaoDAOTest()
    {
        List<AmwayAccountModel> emptyAccountList = amwayAccountDao.lookupAccountsByName(NO_ACCOUNTS);
        assertTrue("No accounts should be returned", emptyAccountList.isEmpty());

        List<AmwayAccountModel> nAccounts= amwayAccountDao.lookupAccountsByName(ALL_ACCOUNTS);
        assertTrue("Accounts list is greater than zero.",nAccounts.size() > 0);
    }
}
