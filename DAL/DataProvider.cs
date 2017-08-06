using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data.SqlClient;
using System.Data;

namespace DAL
{
    class DataProvider
    {

        static string connStr = "HMCHOAN-6V3O0HL\\SQLEXPRESS;AttachDbFilename=|DataDirectory|QLLK.mdf; Integrated Security=True";
        //"Data Source=HMCHOAN-6V3O0HL\SQLEXPRESS;Initial Catalog=QLNS;Integrated Security=True"
        //"Data Source=(LocalDB)\mssqllocaldb;AttachDbFilename=" + System.IO.Directory.GetCurrentDirectory() + "\\QLNS.mdf;" + "Integrated Security=True"


        public static SqlConnection conn;
        public static void OpenConnection()
        {
            try
            {
                conn = new SqlConnection(connStr);
                conn.Open();

            }
            catch
            {
               
            }
        }

        public static void CloseConnection()
        {
            if (!(conn == null))
            {
                conn.Close();
            }
        }

        public static DataTable ExcuteQuery(string Sql)
        {
            OpenConnection();
            DataTable dt = new DataTable();
            SqlCommand comm = conn.CreateCommand();
            comm.CommandText = Sql;
            SqlDataAdapter da = new SqlDataAdapter();
            da.SelectCommand = comm;
            da.Fill(dt);
            CloseConnection();
            return dt;
        }

        public static void ExcuteNonQuery(string sql)
        {
            OpenConnection();
            SqlCommand command = conn.CreateCommand();
            command.CommandText = sql;
            command.ExecuteNonQuery();
            CloseConnection();
        }



    }
}
