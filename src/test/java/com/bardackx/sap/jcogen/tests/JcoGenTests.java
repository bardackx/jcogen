package com.bardackx.sap.jcogen.tests;

import org.junit.Before;

import com.bardackx.sap.jcogen.tests.mockup.FieldMockup;
import com.bardackx.sap.jcogen.tests.mockup.JCoDestinationMockup;
import com.bardackx.sap.jcogen.tests.mockup.JCoFunctionMockup;
import com.bardackx.sap.jcogen.tests.mockup.JCoMetaDataMockup;
import com.bardackx.sap.jcogen.tests.mockup.JCoParameterListMockup;
import com.bardackx.sap.jcogen.tests.mockup.JCoRecordMetaDataMockup;
import com.bardackx.sap.jcogen.tests.mockup.JCoRepositoryMockup;

/**
 * TODO
 * 
 * @author bardackx
 *
 */
public class JcoGenTests {

	private JCoDestinationMockup destination;

	@Before
	public void mockup() {
		destination = new JCoDestinationMockup();

		// MAQUETAR REPOSITORIO
		JCoRepositoryMockup repository = new JCoRepositoryMockup();
		destination.setRepository(repository);

		// MAQUETAR FUNCION
		JCoFunctionMockup function = new JCoFunctionMockup();
		function.setName("ZFMM_MOCKUP_FUNCTION");
		repository.addFunction(function);

		// MAQUETAR DEFINICION DE ESTRUCTURAS (INCLUYE TABLAS)
		{
			{
				JCoRecordMetaDataMockup definition = new JCoRecordMetaDataMockup();
				definition.setName("ZTE_TEST_STRUCTURE_0");

				FieldMockup[] f = new FieldMockup[4];
				for (int i = 0; i < f.length; i++) {
					FieldMockup fx = new FieldMockup();
					fx.setDescription("mockup field");
					fx.setLength(55);
					fx.setName("MOCKUP_STRUCT_FIELD_" + i);
					fx.setRecordTypeName("LKNAD");
					fx.setTypeAsString("CHAR");
					f[i] = fx;
				}
				definition.setFields(f);

				repository.addStructureDefinitions(definition);
			}
			{
				JCoRecordMetaDataMockup definition = new JCoRecordMetaDataMockup();
				definition.setName("ZTT_TEST_TABLE_0");

				FieldMockup[] f = new FieldMockup[5];
				for (int i = 0; i < f.length; i++) {
					FieldMockup fx = new FieldMockup();
					fx.setDescription("mockup field");
					fx.setLength(55);
					fx.setName("MOCKUP_FIELD_" + i);
					fx.setRecordTypeName("LKNAD");
					fx.setTypeAsString("CHAR");
					f[i] = fx;
				}
				definition.setFields(f);

				repository.addStructureDefinitions(definition);
			}
			{
				JCoRecordMetaDataMockup definition = new JCoRecordMetaDataMockup();
				definition.setName("ZTT_TEST_TABLE_1");

				FieldMockup[] f = new FieldMockup[6];
				for (int i = 0; i < f.length; i++) {
					FieldMockup fx = new FieldMockup();
					fx.setDescription("mockup field");
					fx.setLength(55);
					fx.setName("MOCKUP_FIELD_" + i);
					fx.setRecordTypeName("LKNAD");
					fx.setTypeAsString("CHAR");
					f[i] = fx;
				}
				definition.setFields(f);

				repository.addStructureDefinitions(definition);
			}
			{
				JCoRecordMetaDataMockup definition = new JCoRecordMetaDataMockup();
				definition.setName("ZTT_TEST_TABLE_2");

				FieldMockup[] f = new FieldMockup[7];
				for (int i = 0; i < f.length; i++) {
					FieldMockup fx = new FieldMockup();
					fx.setDescription("mockup field");
					fx.setLength(55);
					fx.setName("MOCKUP_FIELD_" + i);
					fx.setRecordTypeName("LKNAD");
					fx.setTypeAsString("CHAR");
					f[i] = fx;
				}
				definition.setFields(f);

				repository.addStructureDefinitions(definition);
			}
			{
				JCoRecordMetaDataMockup definition = new JCoRecordMetaDataMockup();
				definition.setName("ZTT_TEST_TABLE_3");

				FieldMockup[] f = new FieldMockup[8];
				for (int i = 0; i < f.length; i++) {
					FieldMockup fx = new FieldMockup();
					fx.setDescription("mockup field");
					fx.setLength(55);
					fx.setName("MOCKUP_FIELD_" + i);
					fx.setRecordTypeName("LKNAD");
					fx.setTypeAsString("CHAR");
					f[i] = fx;
				}
				definition.setFields(f);

				repository.addStructureDefinitions(definition);
			}
		}

		// MAQUETAR LOS IMPORT PARAMETER LISTS
		{
			FieldMockup[] importParams = new FieldMockup[5];

			importParams[0] = new FieldMockup();
			importParams[0].setName("PARAM_0");
			importParams[0].setDescription("Import parameter 0");
			importParams[0].setRecordTypeName("SAPNONSNS");
			importParams[0].setTypeAsString("CHAR");

			importParams[1] = new FieldMockup();
			importParams[1].setName("PARAM_1");
			importParams[1].setDescription("Import parameter 1");
			importParams[1].setRecordTypeName("SAPNONSNS");
			importParams[1].setTypeAsString("CHAR");

			importParams[2] = new FieldMockup();
			importParams[2].setName("PARAM_2");
			importParams[2].setDescription("Import parameter 2");
			importParams[2].setRecordTypeName("SAPNONSNS");
			importParams[2].setTypeAsString("CHAR");

			importParams[3] = new FieldMockup();
			importParams[3].setName("PARAM_3");
			importParams[3].setDescription("Import parameter 3");
			importParams[3].setRecordTypeName("SAPNONSNS");
			importParams[3].setTypeAsString("CHAR");

			importParams[4] = new FieldMockup();
			importParams[4].setName("PARAM_4");
			importParams[4].setDescription("Import parameter 4, es una estructura segun");
			importParams[4].setRecordTypeName("ZTE_TEST_STRUCTURE_0");
			importParams[4].setTypeAsString("STRUCTURE");

			JCoMetaDataMockup tablesMetadata = new JCoMetaDataMockup();
			tablesMetadata.setName("INPUT"); // <- ES LO QUE REGRESA JCO
			tablesMetadata.setFields(importParams);

			JCoParameterListMockup importParameterList = new JCoParameterListMockup();
			importParameterList.setMetaData(tablesMetadata);

			function.setImportParameterList(importParameterList);
		}

		// MAQUETAR LOS EXPORT PARAMETER LISTS
		{
			FieldMockup[] exportParams = new FieldMockup[5];

			exportParams[0] = new FieldMockup();
			exportParams[0].setName("OUTPARAM_0");
			exportParams[0].setDescription("Export parameter 0");
			exportParams[0].setRecordTypeName("SAPNONSNS");
			exportParams[0].setTypeAsString("CHAR");

			exportParams[1] = new FieldMockup();
			exportParams[1].setName("OUTPARAM_1");
			exportParams[1].setDescription("Export parameter 1");
			exportParams[1].setRecordTypeName("SAPNONSNS");
			exportParams[1].setTypeAsString("CHAR");

			exportParams[2] = new FieldMockup();
			exportParams[2].setName("OUTPARAM_2");
			exportParams[2].setDescription("Export parameter 2");
			exportParams[2].setRecordTypeName("SAPNONSNS");
			exportParams[2].setTypeAsString("CHAR");

			exportParams[3] = new FieldMockup();
			exportParams[3].setName("OUTPARAM_3");
			exportParams[3].setDescription("Export parameter 3");
			exportParams[3].setRecordTypeName("SAPNONSNS");
			exportParams[3].setTypeAsString("CHAR");

			exportParams[4] = new FieldMockup();
			exportParams[4].setName("OUTPARAM_4");
			exportParams[4].setDescription("Export parameter 4, es una estructura segun");
			exportParams[4].setRecordTypeName("ZTE_TEST_STRUCTURE_0");
			exportParams[4].setTypeAsString("STRUCTURE");

			JCoMetaDataMockup tablesMetadata = new JCoMetaDataMockup();
			tablesMetadata.setName("OUTPUT"); // <- ES LO QUE REGRESA JCO
			tablesMetadata.setFields(exportParams);

			JCoParameterListMockup exportParameterList = new JCoParameterListMockup();
			exportParameterList.setMetaData(tablesMetadata);

			function.setExportParameterList(exportParameterList);
		}

		// MAQUETAR LAS TABLAS DE ENTRADA/SALIDA
		{
			FieldMockup[] tables = new FieldMockup[4];

			tables[0] = new FieldMockup();
			tables[0].setName("IT_ZTT_TEST_TABLE_0");
			tables[0].setDescription("Test table 0 description text");
			tables[0].setRecordTypeName("ZTT_TEST_TABLE_0");

			tables[1] = new FieldMockup();
			tables[1].setName("IT_ZTT_TEST_TABLE_1");
			tables[1].setDescription("Test table 1 description text");
			tables[1].setRecordTypeName("ZTT_TEST_TABLE_1");

			tables[2] = new FieldMockup();
			tables[2].setName("IT_ZTT_TEST_TABLE_2");
			tables[2].setDescription("Test table 2 description text");
			tables[2].setRecordTypeName("ZTT_TEST_TABLE_2");

			tables[3] = new FieldMockup();
			tables[3].setName("IT_ZTT_TEST_TABLE_3");
			tables[3].setDescription("Test table 3 description text");
			tables[3].setRecordTypeName("ZTT_TEST_TABLE_3");

			JCoMetaDataMockup tablesMetadata = new JCoMetaDataMockup();
			tablesMetadata.setName("TABLES"); // <- ES LO QUE REGRESA JCO
			tablesMetadata.setFields(tables);

			JCoParameterListMockup tableParameterList = new JCoParameterListMockup();
			tableParameterList.setMetaData(tablesMetadata);

			function.setTableParameterList(tableParameterList);
		}
	}

}
