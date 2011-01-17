#include <OgreOpcodeManager.h>

#define JVIZ_QUERY_MASK 500

namespace Ogre {
    template<> OpcodeManager* Ogre::Singleton<OpcodeManager>::ms_Singleton = 0;
    //-----------------------------------------------------------------------
    OpcodeManager::OpcodeManager()
        : mOpcodeModels(new OpcodeModelList())
        , mFaceCache(new Opcode::CollisionFaces())
        , mRayCollider( new Opcode::RayCollider())
    {
        // setup ray collider
        mRayCollider->SetFirstContact(false);               // report all contacts
        mRayCollider->SetTemporalCoherence(false);          // no temporal coherence
        mRayCollider->SetClosestHit(true);                  // all hits
        mRayCollider->SetCulling(false);                    // with backface culling
        mRayCollider->SetMaxDist(10000.0f);                 // max dist 100 km
        mRayCollider->SetDestination(mFaceCache);           // detected hits go here

        const char* msg = mRayCollider->ValidateSettings();
        if(msg != 0)
        {
            std::cerr << msg << std::endl;
        }
    }
    //-----------------------------------------------------------------------
    OpcodeManager::~OpcodeManager()
    {
        delete mRayCollider;
        delete mFaceCache;

        OpcodeModelList::iterator it = mOpcodeModels->begin();
        while(it != mOpcodeModels->end())
        {
            deleteOpcodeModel( it->second );
            ++it;
        }
        delete mOpcodeModels;   
    }
    //-----------------------------------------------------------------------
    void OpcodeManager::countIndicesAndVertices(Ogre::Entity * entity, size_t & index_count, size_t & vertex_count)
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::countIndicesAndVertices(" << entity->getName() << ")" <<  std::endl;
#endif
        Ogre::MeshPtr mesh = entity->getMesh();
        bool added_shared = false;
        index_count  = 0;
        vertex_count = 0;

        // Calculate how many vertices and indices we're going to need
        for ( unsigned short i = 0; i < mesh->getNumSubMeshes(); ++i)
        {
            Ogre::SubMesh* submesh = mesh->getSubMesh( i );

            // We only need to add the shared vertices once
            if(submesh->useSharedVertices)
            {
                if( !added_shared )
                {
                    vertex_count += mesh->sharedVertexData->vertexCount;
                    added_shared = true;
                }
            }
            else
            {
                vertex_count += submesh->vertexData->vertexCount;
            }

            // Add the indices
            index_count += submesh->indexData->indexCount;
        }
    }
    //-----------------------------------------------------------------------
    void OpcodeManager::convertMeshData(Ogre::Entity * entity, float * vertexData, size_t vertex_count, unsigned int * faceData, size_t index_count)
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::convertMeshData(" << entity->getName() << ")" <<  std::endl;
        std::cout << "\tisHardwareAnimationEnabled=" << entity->isHardwareAnimationEnabled() <<  std::endl;
        std::cout << "\thasVertexAnimation=" << entity->hasVertexAnimation() <<  std::endl;
#endif
        //-------------------------------------------------------------------------
        // CONVERT MESH DATA
        //-------------------------------------------------------------------------
        Ogre::MeshPtr mesh = entity->getMesh();
        bool added_shared = false;
        size_t current_offset = 0;
        size_t shared_offset = 0;
        size_t next_offset = 0;
        size_t index_offset = 0;
        unsigned int numOfSubs = 0;

        // Run through the submeshes again, adding the data into the arrays
        for ( unsigned short i = 0; i < mesh->getNumSubMeshes(); ++i)
        {
            Ogre::SubMesh* submesh = mesh->getSubMesh(i);
            bool useSharedVertices = submesh->useSharedVertices;

            //---------------------------------------------------------------------
            // GET VERTEXDATA
            //---------------------------------------------------------------------
            const Ogre::VertexData * vertex_data = 0;
            if(entity->isHardwareAnimationEnabled() && entity->hasVertexAnimation())
            {
                vertex_data = useSharedVertices ? entity->_getHardwareVertexAnimVertexData() : entity->getSubEntity(i)->_getHardwareVertexAnimVertexData();
            }
            else
            {
                vertex_data = useSharedVertices ? mesh->sharedVertexData : submesh->vertexData;
            }

            if((!useSharedVertices)||(useSharedVertices && !added_shared))
            {
                if(useSharedVertices)
                {
                    added_shared = true;
                    shared_offset = current_offset;
                }

                const Ogre::VertexElement* posElem =
                    vertex_data->vertexDeclaration->findElementBySemantic(Ogre::VES_POSITION);

                Ogre::HardwareVertexBufferSharedPtr vbuf =
                    vertex_data->vertexBufferBinding->getBuffer(posElem->getSource());

                unsigned char* vertex =
                    static_cast<unsigned char*>(vbuf->lock(Ogre::HardwareBuffer::HBL_READ_ONLY));

                // There is _no_ baseVertexPointerToElement() which takes an Ogre::Real or a double
                //  as second argument. So make it float, to avoid trouble when Ogre::Real will
                //  be comiled/typedefed as double:
                //      Ogre::Real* pReal;
                float* pReal;

                for( size_t j = 0; j < vertex_data->vertexCount; ++j, vertex += vbuf->getVertexSize())
                {
                    posElem->baseVertexPointerToElement(vertex, &pReal);

                    size_t n = current_offset*3 + j*3;
                    vertexData[n + 0] = pReal[0];
                    vertexData[n + 1] = pReal[1];
                    vertexData[n + 2] = pReal[2];
                }

                vbuf->unlock();
                next_offset += vertex_data->vertexCount;
            }

            //---------------------------------------------------------------------
            // GET INDEXDATA
            //---------------------------------------------------------------------      
            Ogre::IndexData* index_data = submesh->indexData;
            if (index_data->indexCount > 0)
			{
				size_t numTris = index_data->indexCount / 3;
				Ogre::HardwareIndexBufferSharedPtr ibuf = index_data->indexBuffer;

				bool use32bitindexes = (ibuf->getType() == Ogre::HardwareIndexBuffer::IT_32BIT);

				unsigned long*  pLong = static_cast<unsigned long*>(ibuf->lock(Ogre::HardwareBuffer::HBL_READ_ONLY));
				unsigned short* pShort = reinterpret_cast<unsigned short*>(pLong);


				size_t offset = (submesh->useSharedVertices)? shared_offset : current_offset;

				if ( use32bitindexes )
				{
					for ( size_t k = 0; k < numTris*3; ++k)
					{
						faceData[index_offset++] = pLong[k] + static_cast<unsigned long>(offset);
					}
				}
				else
				{
					for ( size_t k = 0; k < numTris*3; ++k)
					{
						faceData[index_offset++] = static_cast<unsigned long>(pShort[k]) + static_cast<unsigned long>(offset);
					}
				}

				ibuf->unlock();
				current_offset = next_offset;
			}
        }
    }

    //-----------------------------------------------------------------------
    Opcode::Model * OpcodeManager::createOpcodeModel(Ogre::Entity * ogreEntity)
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::createOpcodeModel(" << ogreEntity->getName() << ")" <<  std::endl;
#endif
        //-------------------------------------------------------------------------
        // CONVERT MESH DATA
        //-------------------------------------------------------------------------
        bool added_shared = false;
        size_t current_offset = 0;
        size_t shared_offset = 0;
        size_t next_offset = 0;
        size_t index_offset = 0;
        size_t vertex_count = 0;
        size_t index_count  = 0;

        countIndicesAndVertices(ogreEntity, index_count, vertex_count);

        // Allocate space for the vertices and indices
        float* vertexData = new float[vertex_count * 3];
        unsigned int*   faceData = new unsigned int[index_count];

        convertMeshData(ogreEntity, vertexData, vertex_count, faceData, index_count );

        //---------------------------------------------------------------------
        // INIT OPCODE
        //---------------------------------------------------------------------
        unsigned int numFaces = index_count / 3;
        unsigned int numVertices = vertex_count;

        Opcode::MeshInterface * opcMeshAccess = new Opcode::MeshInterface();
        opcMeshAccess->SetNbTriangles(numFaces);
        opcMeshAccess->SetNbVertices(numVertices);
        opcMeshAccess->SetPointers((IndexedTriangle*)faceData, (Point*)vertexData);
        //opcMeshAccess->SetStrides(sizeof(int) * 3, sizeof(float) * 3);

        // Build tree
        Opcode::BuildSettings Settings;
        Settings.mRules = Opcode::SPLIT_BEST_AXIS; // dunno what this means, stole it from ODE :)

        Opcode::OPCODECREATE opcc;
        opcc.mIMesh = opcMeshAccess;
        opcc.mSettings.mRules = Opcode::SPLIT_SPLATTER_POINTS;   // split by splattering primitive centers (???)
        opcc.mSettings.mLimit = 1;              // build a complete tree, 1 primitive/node
        opcc.mNoLeaf    = false; // true;
        opcc.mQuantized = false; // true;

        Opcode::Model * opcModel = new Opcode::Model();
        opcModel->Build(opcc);
        return opcModel;
    }

    //-----------------------------------------------------------------------
    void OpcodeManager::deleteOpcodeModel(Opcode::Model * model)
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::deleteOpcodeModel" << std::endl;
#endif

        const Opcode::MeshInterface * mesh = model->GetMeshInterface();
        delete model;
        
        if(mesh == 0)
        {
            std::cerr << "OpcodeManager::deleteOpcodeModel: model->GetMeshInterface() == 0" << std::endl;
            return;
        }

        int * indices = (int*)mesh->GetTris();
        float * vertices = (float*)mesh->GetVerts();
        delete [] indices;
        delete [] vertices;
        delete mesh;
    }

    //-----------------------------------------------------------------------
    Opcode::CollisionFace * OpcodeManager::lineCheck(Ogre::Entity * ogreEntity, 
        Opcode::Model * opcModel,
        Ogre::Ray const & line,
        int &numFaces)
    {
        // convert Matrix4 to Opcode Matrix4x4
        Ogre::Matrix4 ownMatrix;
        IceMaths::Matrix4x4 opcMatrix;

        // if entity has a skeleton
        if (! ogreEntity->hasSkeleton()) 
        {
            ogreEntity->getParentSceneNode()->getWorldTransforms(&ownMatrix);
            for(unsigned int i = 0; i < 4; i++)
            {
                opcMatrix.m[0][i] = ownMatrix[i][0];
                opcMatrix.m[1][i] = ownMatrix[i][1];
                opcMatrix.m[2][i] = ownMatrix[i][2];
                opcMatrix.m[3][i] = ownMatrix[i][3];
            }
        } 
        else 
        {
            opcMatrix.Identity();
        }


        // build Opcode ray from line
        IceMaths::Ray ray;
        ray.mOrig.x = line.getOrigin().x;
        ray.mOrig.y = line.getOrigin().y;
        ray.mOrig.z = line.getOrigin().z;
        ray.mDir.x = line.getDirection().x;
        ray.mDir.y = line.getDirection().y;
        ray.mDir.z = line.getDirection().z;  

        // perform collision
        mRayCollider->Collide(ray, *opcModel, &opcMatrix);

        // get collision result
        if (mRayCollider->GetContactStatus())
        {
            // fill out contact point and collision normal of closest contact
            numFaces = mFaceCache->GetNbFaces();
            Opcode::CollisionFace * collFaces = new Opcode::CollisionFace[numFaces];
            const Opcode::CollisionFace * locCollFaces = mFaceCache->GetFaces();
            for(int i=0; i<numFaces; ++i)
            {
                Opcode::CollisionFace face;
                face.mFaceID = locCollFaces[i].mFaceID;
                face.mDistance = locCollFaces[i].mDistance;
                face.mU = locCollFaces[i].mU;
                face.mV = locCollFaces[i].mV;
                collFaces[i] = face;
            }
            return collFaces;
        }
        return 0;
    }

    //-----------------------------------------------------------------------
    const Opcode::CollisionFace OpcodeManager::collisionOnSurface( Ogre::Entity * ogreEntity,  Ogre::Ray const & line )
    {
        OpcodeModelList::iterator it = mOpcodeModels->find(ogreEntity);
        Opcode::Model * opcodeModel = 0;
        bool tempModel = false;

        if (it == mOpcodeModels->end())
        {
            opcodeModel = createOpcodeModel(ogreEntity);
            tempModel = true;
        }
        else
        {
            opcodeModel = it->second;
        }
        //---------------------------------------------------------------------
        // LINE CHECK
        //---------------------------------------------------------------------
        int numCollideFaces = 0;
        float distance = -1;
        Opcode::CollisionFace * collideFaces = lineCheck(ogreEntity, opcodeModel, line, numCollideFaces);   
        Opcode::CollisionFace closestFace;
        closestFace.mDistance = -1;

        if(collideFaces != 0)
        {         
            if (numCollideFaces > 0 && collideFaces != 0)
            {
                // if in closest hit mode, find the contact with the smallest distance      
                int collFaceIndex = 0;

                for (int i = 0; i < numCollideFaces; i++)
                {
                    if (collideFaces[i].mDistance < collideFaces[collFaceIndex].mDistance)
                    {
                        collFaceIndex = i;
                    }
                }

                closestFace = collideFaces[collFaceIndex];
            }
        }

        if(tempModel)
        {
            deleteOpcodeModel(opcodeModel);
        }

        return closestFace;
    }

    //-----------------------------------------------------------------------
    const Opcode::CollisionFace OpcodeManager::getHitCoordinates( Ogre::Entity * ogreEntity,  Ogre::Ray const & line, Ogre::Vector3 & hitCoord, Ogre::Vector3 & normal)
    {
        OpcodeModelList::iterator it = mOpcodeModels->find(ogreEntity);
        Opcode::Model * opcodeModel = 0;
        bool tempModel = false;

        if (it == mOpcodeModels->end())
        {
            opcodeModel = createOpcodeModel(ogreEntity);
            tempModel = true;
        }
        else
        {
            opcodeModel = it->second;
        }


        //---------------------------------------------------------------------
        // LINE CHECK
        //---------------------------------------------------------------------
        int numCollideFaces = 0;
        float distance = -1;
        Opcode::CollisionFace * collideFaces = lineCheck(ogreEntity, opcodeModel, line, numCollideFaces);
        Opcode::CollisionFace closestFace;
        closestFace.mDistance = -1;

        if(collideFaces != 0)
        {         
            if (numCollideFaces > 0 && collideFaces != 0)
            {
                // if in closest hit mode, find the contact with the smallest distance      
                int collFaceIndex = 0;

                for (int i = 0; i < numCollideFaces; i++)
                {
                    if (collideFaces[i].mDistance < collideFaces[collFaceIndex].mDistance)
                    {
                        collFaceIndex = i;
                    }
                }

                closestFace = collideFaces[collFaceIndex];
            }

            Opcode::VertexPointers vp;
            const Opcode::MeshInterface * opcMeshAccess = opcodeModel->GetMeshInterface();
            opcMeshAccess->GetTriangle(vp, closestFace.mFaceID);
            IceMaths::Triangle tri(*vp.Vertex[0], *vp.Vertex[1], *vp.Vertex[2]);

            IceMaths::Point iceNormal;
            tri.Normal(iceNormal);
            iceNormal.Normalize();
            normal.x = iceNormal.x;
            normal.y = iceNormal.y;
            normal.z = iceNormal.z;

            IceMaths::Point iceHitPoint;
            tri.ComputePoint(closestFace.mU, closestFace.mV, iceHitPoint);
            hitCoord.x = iceHitPoint.x;
            hitCoord.y = iceHitPoint.y;
            hitCoord.z = iceHitPoint.z;

#ifdef _DEBUG
            std::cout << "Hitted Face:" << std::endl;
            std::cout << "\tVertex0: (" << vp.Vertex[0]->x << ", " << vp.Vertex[0]->y << ", " << vp.Vertex[0]->z << ")" << std::endl;
            std::cout << "\tVertex1: (" << vp.Vertex[1]->x << ", " << vp.Vertex[1]->y << ", " << vp.Vertex[1]->z << ")" << std::endl;
            std::cout << "\tVertex2: (" << vp.Vertex[2]->x << ", " << vp.Vertex[2]->y << ", " << vp.Vertex[2]->z << ")" << std::endl;
            std::cout << "\tNormal: (" << normal.x << ", " << normal.y << ", " << normal.z << ")" << std::endl;
            std::cout << "\tIntersection point: (" << hitCoord.x << ", " << hitCoord.y << ", " << hitCoord.z << ")" << std::endl;
#endif
        }

        if(tempModel)
        {
            deleteOpcodeModel(opcodeModel);
        }

        return closestFace;
    }
    //-----------------------------------------------------------------------
    void OpcodeManager::addEntity( Ogre::Entity* entity )
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::addEntity(" << entity->getName() << ")" <<  std::endl;
#endif
        OpcodeModelList::iterator it = mOpcodeModels->find(entity);

        if (it != mOpcodeModels->end())
        {
            // already cached
            return;
        }

        Opcode::Model* opcodeModel = createOpcodeModel(entity);
        mOpcodeModels->insert(OpcodeModelList::value_type(entity, opcodeModel));
    }
    //-----------------------------------------------------------------------
    void OpcodeManager::removeEntity( Ogre::Entity* entity )
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::removeEntity" << std::endl;
#endif
        OpcodeModelList::iterator it = mOpcodeModels->find(entity);
        if (it == mOpcodeModels->end())
        {
            return;
        }

        deleteOpcodeModel( it->second );
        mOpcodeModels->erase(entity);
    }
    //-----------------------------------------------------------------------
    Ogre::Entity * OpcodeManager::getClosestEntity(Ogre::RaySceneQuery * rayQuery)
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::getClosestEntity" << std::endl;
#endif
        Ogre::Entity * resultEntity = 0;

        rayQuery->setSortByDistance(true);

        Ogre::RaySceneQueryResult result = rayQuery->execute();    
        Ogre::RaySceneQueryResult::iterator itr = result.begin();

        float minDist = 0xFFFFFFFF;
        for ( itr = result.begin( ); itr != result.end(); itr++ )
        {
            if ( (itr->movable) && (itr->movable->getMovableType()=="Entity") )
            {
                Ogre::Entity * entity = static_cast<Ogre::Entity*>(itr->movable);
                Opcode::CollisionFace closestFace = collisionOnSurface(entity, rayQuery->getRay());

                if (closestFace.mDistance != -1 && closestFace.mDistance < minDist)
                {
                    minDist = closestFace.mDistance;
                    resultEntity = entity;
                }
            }
        }

        rayQuery->clearResults();
        return resultEntity;
    }

    //-----------------------------------------------------------------------
    bool OpcodeManager::getClosestHit(Ogre::RaySceneQuery * rayQuery,
        Ogre::Vector3 &hitCoord, Ogre::Vector3 & normal)
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::getClosestHit" << std::endl;
#endif
        Opcode::CollisionFace closestFace;
        rayQuery->setSortByDistance(true);

        Ogre::RaySceneQueryResult result = rayQuery->execute();    
        Ogre::RaySceneQueryResult::iterator itr = result.begin();

        float minDist = 0xFFFFFFFF;
        bool hit = false;

        for ( itr = result.begin( ); itr != result.end(); itr++ )
        {
            if ( (itr->movable) && (itr->movable->getMovableType()=="Entity") )
            {
                Ogre::Entity * entity = static_cast<Ogre::Entity*>(itr->movable);
                Ogre::Vector3 locHitCoord;
                Ogre::Vector3 locNormal;
                Opcode::CollisionFace face = getHitCoordinates( entity, 
                    rayQuery->getRay(),
                    locHitCoord,
                    locNormal);
                if (face.mDistance != -1 && face.mDistance < minDist)
                {
                    minDist = face.mDistance;
                    closestFace = face;
                    Ogre::Matrix4 matrix;
                    entity->getParentNode()->getWorldTransforms(&matrix);
                    hitCoord = matrix * locHitCoord;
                    Ogre::Matrix3 rotMat;
                    matrix.extract3x3Matrix(rotMat);
                    normal = rotMat * locNormal;
                    hit = true;
                }
            }
        }

#ifdef _DEBUG
        //std::cout << "\tWorldTransform: " << Ogre::StringConverter::toString(matrix) << std::endl;
        std::cout << "\tHit: " << hit << std::endl;
        std::cout << "\tWorld Normal: (" << normal.x << ", " << normal.y << ", " << normal.z << ")" << std::endl;
        std::cout << "\tWorld Intersection point: (" << hitCoord.x << ", " << hitCoord.y << ", " << hitCoord.z << ")" << std::endl;
#endif

        rayQuery->clearResults();
        return hit;
    }

    //-----------------------------------------------------------------------
    Ogre::Entity* OpcodeManager::executeQuery(Ogre::RaySceneQuery * rayQuery,
        Ogre::Vector3 &hitCoord, Ogre::Vector3 & normal)
    {
#ifdef _DEBUG
        std::cout << "OpcodeManager::getClosestHit" << std::endl;
#endif
        Ogre::Entity *resultEntity = 0;
        Opcode::CollisionFace closestFace;
        rayQuery->setSortByDistance(true);

        Ogre::RaySceneQueryResult result = rayQuery->execute();    
        Ogre::RaySceneQueryResult::iterator itr = result.begin();

        float minDist = 0xFFFFFFFF;
        bool hit = false;

        for ( itr = result.begin( ); itr != result.end(); itr++ )
        {
            if ( (itr->movable) && (itr->movable->getMovableType()=="Entity") )
            {
                Ogre::Entity * entity = static_cast<Ogre::Entity*>(itr->movable);
                Ogre::Vector3 locHitCoord;
                Ogre::Vector3 locNormal;
                Opcode::CollisionFace face = getHitCoordinates( entity, 
                    rayQuery->getRay(),
                    locHitCoord,
                    locNormal);
                if (face.mDistance != -1 && face.mDistance < minDist)
                {
                    minDist = face.mDistance;
                    closestFace = face;
                    Ogre::Matrix4 matrix;
                    entity->getParentNode()->getWorldTransforms(&matrix);
                    hitCoord = matrix * locHitCoord;
                    Ogre::Matrix3 rotMat;
                    matrix.extract3x3Matrix(rotMat);
                    normal = rotMat * locNormal;
                    hit = true;
                    resultEntity = entity;
                }
            }
        }

#ifdef _DEBUG
        //std::cout << "\tWorldTransform: " << Ogre::StringConverter::toString(matrix) << std::endl;
        std::cout << "\tHit: " << hit << std::endl;
        std::cout << "\tWorld Normal: (" << normal.x << ", " << normal.y << ", " << normal.z << ")" << std::endl;
        std::cout << "\tWorld Intersection point: (" << hitCoord.x << ", " << hitCoord.y << ", " << hitCoord.z << ")" << std::endl;
#endif

        rayQuery->clearResults();
        return resultEntity;
    }
}